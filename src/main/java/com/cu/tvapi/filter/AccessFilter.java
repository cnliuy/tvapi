package com.cu.tvapi.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StreamUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 
 * http://www.troylc.cc/spring-cloud/2017/03/19/spirng-cloud-apigateway.html
 * SpringCloud和docker之微服务-apigateway(四)
 * 
 * --- 服务过滤
 *
 * 自定义过滤器的实现，需要继承ZuulFilter，需要重写实现下面四个方法：
 * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
 * 	pre：可以在请求被路由之前调用
 * 	routing：在路由请求时候被调用
 * 	post：在routing和error过滤器之后被调用
 * 	error：处理请求时发生错误时被调用
 * 
 * 	filterOrder：通过int值来定义过滤器的执行顺序
 * 
 * 	shouldFilter：返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。 * 
 * 	在上例中，我们直接返回true，所以该过滤器总是生效。
 * 	
 * 	run：过滤器的具体逻辑。
 * 		需要注意，这里我们通过ctx.setSendZuulResponse(false)令zuul过滤该请求，
 * 		不对其进行路由，然后通过ctx.setResponseStatusCode(401)设置了其返回的错误码，
 * 		当然我们也可以进一步优化我们的返回，比如，通过ctx.setResponseBody(body)对返回body内容进行编辑，
 * 		如果有中文乱码。则可以：ctx.getResponse().setContentType("text/html;charset=UTF-8")
 * */
public class AccessFilter extends ZuulFilter  {
	
    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);
    
    @Override
    public String filterType() {
    	//return "pre";  
        return "post";
    }
    
    @Override
    public int filterOrder() {
        //return 0;     //pre	
        //return 1;     //pre
        return 999;     //post
    }
    
    @Override
    public boolean shouldFilter() { 
        return true;
    }
    
    @Override
    public Object run() {
    	
        //RequestContext ctx = RequestContext.getCurrentContext();        
        //HttpServletRequest request = ctx.getRequest();
        //HttpServletResponse response = ctx.getResponse();
        
        //log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        System.out.println("===================================");
               
        /**
         * 校验 token
         * 
         * */
        //Object accessToken = request.getParameter("accessToken");
        //if(accessToken == null) {
        //    log.warn("access token is empty");
        //    ctx.setSendZuulResponse(false);
        //    ctx.setResponseStatusCode(401);
        //    return null;
        //}
        
       
        //log.info("access token ok");
        
        
        /**
         * 参考 https://springcloud.cc/spring-cloud-netflix.html
         *    如何编写路由过滤器+
         *    
         * https://github.com/spring-cloud-samples/sample-zuul-filters/tree/master/src/main/java/org/springframework/cloud/samplezuulfilters    
         * https://github.com/spring-cloud-samples/sample-zuul-filters/blob/master/src/main/java/org/springframework/cloud/samplezuulfilters/ModifyResponseBodyFilter.java 
         * https://github.com/spring-cloud-samples/sample-zuul-filters/blob/master/src/main/java/org/springframework/cloud/samplezuulfilters/ModifyResponseDataStreamFilter.java   
         * 
         * */

        //RequestContext context = RequestContext.getCurrentContext();
        //String stream = context.getResponseBody();
        //System.out.println("body:"+stream);
        
		 try {			
		 
			RequestContext context = RequestContext.getCurrentContext();  
			InputStream stream = context.getResponseDataStream(); 
			String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
			context.setResponseBody("Modified via setResponseBody(): "+stream);
			System.out.println("body:"+stream);
			
			
			//RequestContext context = RequestContext.getCurrentContext();
			//InputStream stream = context.getResponseDataStream();
			//String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
			//body = "Modified via setResponseDataStream(): " + body;
			//System.out.println("body:"+body);
			//context.setResponseDataStream(new ByteArrayInputStream(body.getBytes("UTF-8")));
		} catch (IOException e) {
			ReflectionUtils.rethrowRuntimeException(e);
		}       
        
   
			
        
        return null;
    }
}