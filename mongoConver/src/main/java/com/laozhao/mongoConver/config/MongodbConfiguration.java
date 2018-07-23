package com.laozhao.mongoConver.config;

import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.mongodb.MongoClientURI;

@Configuration
public class MongodbConfiguration {
    private String mongoUrl="mongodb://localhost:27017/testMongo";
 
    @Bean
    public MongoMappingContext mongoMappingContext() {
        MongoMappingContext mappingContext = new MongoMappingContext();
        return mappingContext;
    }
 
    @Bean
    @Primary
    public MongoDbFactory dbFactory1() throws UnknownHostException {
        return new SimpleMongoDbFactory(new MongoClientURI(mongoUrl));
    }
 
    @Bean
    public MappingMongoConverter mappingMongoConverter1() throws Exception {
        DefaultDbRefResolver dbRefResolver = new DefaultDbRefResolver(this.dbFactory1());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, this.mongoMappingContext());
        List<Converter> converters = new ArrayList();
        converters.add(new DoubleToBigDecimalConverter());
        converters.add(new DateToString());
        converters.add(new StringToDate());
        CustomConversions customConversions = new CustomConversions(converters);
        converter.setCustomConversions(customConversions);
        return converter;
    }
 
    @Bean
    @Primary
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(this.dbFactory1(), this.mappingMongoConverter1());
    }
}
 
@ReadingConverter
class DoubleToBigDecimalConverter implements Converter<Double,BigDecimal> {
    public BigDecimal convert(Double source) {
        BigDecimal bigDecimal = BigDecimal.valueOf(source);
        return bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
    }
}    
@WritingConverter
class DateToString implements Converter<Date,String>{

	public String convert(Date source) {
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
		return sf.format(source);
	}
	
}

@ReadingConverter
class StringToDate implements Converter<String,Date>{

	public Date convert(String source) {
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
		try {
			return sf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}
	
}

