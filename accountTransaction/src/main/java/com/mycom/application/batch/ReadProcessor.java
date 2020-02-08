package com.mycom.application.batch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mycom.mybatis.model.Transaction;

public class ReadProcessor {
	
	public List<Transaction> readTextConvertToJson(String filePath) {
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		try (Stream<Path> walk = Files.walk(Paths.get(filePath)))  {
			List<String> result = walk.filter(Files::isRegularFile)
					.map(x -> x.toString()).collect(Collectors.toList());

			result.forEach(x -> {
				try {
					List<String> contents = Files.lines(Paths.get(x)).collect(Collectors.toList());
					contents.stream().skip(1).forEach(y -> {
						transactions.add(toTransactionObj(y.split("\\|")));
						}
					);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return transactions;
	}
	
	private Transaction toTransactionObj(String[] arrStr) {		
		int cnt = 0;
		Transaction trx = new Transaction();
		for(String str:arrStr) {
			if(cnt == 0) {
				trx.setAccountNo(str);
			} else if(cnt == 1) {
				trx.setAmount(str);
			} else if(cnt == 2) {
				trx.setDescription(str);
			} else if(cnt == 3) {
				trx.setTrxDate(str);
			} else if(cnt == 4) {
				trx.setTrxTime(str);
			} else if(cnt == 5) {
				trx.setCustomerId(str);
			} else {
				break;
			}
			
			cnt++;
		}
		return trx;
	}

}
