package com.mycom.application.batch;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.mycom.application.service.TransactionService;
import com.mycom.mybatis.model.Transaction;

public class ReadTask implements Tasklet{
	private final Log log = LogFactory.getLog(this.getClass());
	
	private TransactionService transactionService;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		ReadProcessor rp = new ReadProcessor();
		List<Transaction> transactions = rp.readTextConvertToJson("D:\\Dev2019\\workspace\\accountTransactionApi\\accountTransaction\\files"); 
		
		for(Transaction transaction:transactions) {
			log.info(transaction.getAccountNo());
			this.transactionService.saveTransaction(transaction);
		}
		log.info("Process done");
        return RepeatStatus.FINISHED;
	}

    public ReadTask(TransactionService transactionService){
        this.transactionService = transactionService;
    }
}
