package com.mycom.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.mycom.application.dto.TransactionDTO;

public interface TransactionMapper {

	@Select("select * from ("
			+ "select rownum rnum, paging.* from ("
			+ "select a.account_no accountNo, a.amount, a.description, TO_CHAR(trx_date, 'YYYY-MM-DD') trxDate, TO_CHAR(trx_date, 'HH24:MI:SS') trxTime, c.customer_id customerId "  
			+ "FROM TRANSACTIONS a, ACCOUNTS b, CUSTOMERS c where a.account_no = b.account_no and b.customer_id = c.customer_id"
			+ ") paging"
			+ ") where rnum between #{startNum} and #{endNum}")
	List<TransactionDTO> getTransactions(Map<String, String> params);
	
	@Select("select * from ("
			+ "select rownum rnum, paging.* from ("
			+ "select a.account_no accountNo, a.amount, a.description, TO_CHAR(trx_date, 'YYYY-MM-DD') trxDate, TO_CHAR(trx_date, 'HH24:MI:SS') trxTime, c.customer_id customerId "  
			+ "FROM TRANSACTIONS a, ACCOUNTS b, CUSTOMERS c where a.account_no = b.account_no and b.customer_id = c.customer_id " 
			+ "and a.account_no = #{accountNo}"
			+ ") paging"
			+ ") where rnum between #{startNum} and #{endNum}")
	List<TransactionDTO> getTransactionsByAccountNo(Map<String, String> params);
	
	@Select("select * from ("
			+ "select rownum rnum, paging.* from ("
			+"select a.account_no accountNo, a.amount, a.description, TO_CHAR(trx_date, 'YYYY-MM-DD') trxDate, TO_CHAR(trx_date, 'HH24:MI:SS') trxTime, c.customer_id customerId "  
			+"FROM TRANSACTIONS a, ACCOUNTS b, CUSTOMERS c where a.account_no = b.account_no and b.customer_id = c.customer_id " 
			+"and a.description = #{description}"
			+ ") paging"
			+ ") where rnum between #{startNum} and #{endNum}")
	List<TransactionDTO> getTransactionsByDesc(Map<String, String> params);
	
	@Select("select * from ("
			+ "select rownum rnum, paging.* from ("
			+"select a.account_no accountNo, a.amount, a.description, TO_CHAR(trx_date, 'YYYY-MM-DD') trxDate, TO_CHAR(trx_date, 'HH24:MI:SS') trxTime, c.customer_id customerId "  
			+"FROM TRANSACTIONS a, ACCOUNTS b, CUSTOMERS c where a.account_no = b.account_no and b.customer_id = c.customer_id " 
			+"and c.customer_id = #{customerId}"
			+ ") paging"
			+ ") where rnum between #{startNum} and #{endNum}")
	List<TransactionDTO> getTransactionsByCustomerId(Map<String, String> params);
}
