package com.mycom.mybatis.mapper;

import com.mycom.mybatis.model.Address;
import com.mycom.mybatis.model.Article;
import com.mycom.mybatis.model.Person;
import org.apache.ibatis.annotations.*;

public interface AddressMapper {

	@Insert("Insert into address (streetAddress,personId) values(#{streetAddress},#{personId})")
	@Options(useGeneratedKeys = true) //, flushCache = true
	public Integer saveAddress(Address address);

	@Select("SELECT addressId, streetAddress FROM Address WHERE addressId = #{addressId}")
	@Results(value = { @Result(property = "addressId", column = "addressId"),
			@Result(property = "streetAddress", column = "streetAddress"),
			@Result(property = "person", column = "personId", javaType = Person.class, one = @One(select = "getPerson")) })
	Address getAddresses(Integer addressID);

	@Select("SELECT personId FROM address WHERE addressId = #{addressId})")
	Person getPerson(Integer personId);
	
	@Select("SELECT title, author FROM articles WHERE id = 1")
	Article getArticle();
	
	@Select("SELECT title, author FROM articles WHERE id = #{articleId}")
	Article getArticleById(Integer id);
}
