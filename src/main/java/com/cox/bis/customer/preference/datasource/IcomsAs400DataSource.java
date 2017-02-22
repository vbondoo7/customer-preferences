package com.cox.bis.customer.preference.datasource;

import javax.annotation.PostConstruct;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("datasource.icomssql") 
public class IcomsAs400DataSource {
	
	private String url;
	private String username;
	private String password;
	private String driverClassName;
	private String initialSize;
	private String maxActive;
	private String minIdle;
	private String maxIdle;
	private String validationQueryTimeout;
	private String validationQuery;
	private String minEvictableIdleTimeMillis;
	private String timeBetweenEvictionRunsMillis;
	private String numTestsPerEvictionRun;
	private String testOnBorrow;
	private String testOnReturn;
	private String testWhileIdle;
	private String removeAbandonedTimeout;
	private String removeAbandoned;
	private String logAbandoned;
	
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getInitialSize() {
		return initialSize;
	}
	public void setInitialSize(String initialSize) {
		this.initialSize = initialSize;
	}
	public String getMaxActive() {
		return maxActive;
	}
	public void setMaxActive(String maxActive) {
		this.maxActive = maxActive;
	}
	
	public String getMinIdle() {
		return minIdle;
	}
	public void setMinIdle(String minIdle) {
		this.minIdle = minIdle;
	}
	public String getMaxIdle() {
		return maxIdle;
	}
	public void setMaxIdle(String maxIdle) {
		this.maxIdle = maxIdle;
	}
	public String getValidationQueryTimeout() {
		return validationQueryTimeout;
	}
	public void setValidationQueryTimeout(String validationQueryTimeout) {
		this.validationQueryTimeout = validationQueryTimeout;
	}
	public String getValidationQuery() {
		return validationQuery;
	}
	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}
	public String getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}
	public void setMinEvictableIdleTimeMillis(String minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}
	public String getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}
	public void setTimeBetweenEvictionRunsMillis(
			String timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}
	public String getNumTestsPerEvictionRun() {
		return numTestsPerEvictionRun;
	}
	public void setNumTestsPerEvictionRun(String numTestsPerEvictionRun) {
		this.numTestsPerEvictionRun = numTestsPerEvictionRun;
	}
	public String getTestOnBorrow() {
		return testOnBorrow;
	}
	public void setTestOnBorrow(String testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}
	public String getTestOnReturn() {
		return testOnReturn;
	}
	public void setTestOnReturn(String testOnReturn) {
		this.testOnReturn = testOnReturn;
	}
	public String getTestWhileIdle() {
		return testWhileIdle;
	}
	public void setTestWhileIdle(String testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}
	public String getRemoveAbandonedTimeout() {
		return removeAbandonedTimeout;
	}
	public void setRemoveAbandonedTimeout(String removeAbandonedTimeout) {
		this.removeAbandonedTimeout = removeAbandonedTimeout;
	}
	public String getRemoveAbandoned() {
		return removeAbandoned;
	}
	public void setRemoveAbandoned(String removeAbandoned) {
		this.removeAbandoned = removeAbandoned;
	}
	public String getLogAbandoned() {
		return logAbandoned;
	}
	public void setLogAbandoned(String logAbandoned) {
		this.logAbandoned = logAbandoned;
	}
	
	@Bean(name = "icomsAs400Sql")
	@Primary
	@PostConstruct
	public javax.sql.DataSource primaryDataSource() {
		DataSource dataSource = new DataSource();
		dataSource.setUrl(url);
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setMinIdle(Integer.parseInt(minIdle));
		dataSource.setMaxIdle(Integer.parseInt(maxIdle));
		dataSource.setMaxActive(Integer.parseInt(maxActive));
		dataSource.setInitialSize(Integer.parseInt(initialSize));
		dataSource.setValidationQueryTimeout(Integer.parseInt(validationQueryTimeout));
		dataSource.setValidationQuery(validationQuery);
		dataSource.setMinEvictableIdleTimeMillis(Integer.parseInt(minEvictableIdleTimeMillis));
		dataSource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(timeBetweenEvictionRunsMillis));
		dataSource.setNumTestsPerEvictionRun(Integer.parseInt(numTestsPerEvictionRun));
		dataSource.setTestOnBorrow(Boolean.valueOf(testOnBorrow));
		dataSource.setTestOnReturn(Boolean.valueOf(testOnReturn));
		dataSource.setTestWhileIdle(Boolean.valueOf(testWhileIdle));
		dataSource.setRemoveAbandonedTimeout(Integer.parseInt(removeAbandonedTimeout));
		dataSource.setRemoveAbandoned(Boolean.valueOf(removeAbandoned));
		dataSource.setLogAbandoned(Boolean.valueOf(logAbandoned));
		
        return dataSource; 
	}
}