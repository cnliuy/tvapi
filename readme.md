##zuul的使用 

1.  
 注意 pom.xml中的  

	<properties>  
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>  
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>  
		<java.version>1.8</java.version>  
		<spring-cloud.version>Dalston.SR3</spring-cloud.version>  
		<aspectj.version>1.8.9</aspectj.version> <!-- 需要有，否则 org.aspectj.lang.JoinPoint 类似的erro-->  
	</properties>  
否则会报错  
  