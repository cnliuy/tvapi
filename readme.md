## zuul的使用 
  
  
### zuul 简单使用 ,基于Spring cloud 1.5.7  
  

1.注意 pom.xml中的  aspectj.version属性 ： 

	<properties>  
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>  
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>  
		<java.version>1.8</java.version>  
		<spring-cloud.version>Dalston.SR3</spring-cloud.version>  
		<aspectj.version>1.8.9</aspectj.version><!-- 需要有，否则 org.aspectj.lang.JoinPoint 类似的erro -->  
	</properties>  
   缺失否则会报错。  
   
2.访问地址
   http://127.0.0.1:8888/it