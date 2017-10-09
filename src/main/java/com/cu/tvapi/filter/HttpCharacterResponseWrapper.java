package com.cu.tvapi.filter;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class HttpCharacterResponseWrapper extends HttpServletResponseWrapper{
    private CharArrayWriter ref_charArrayWriter = new CharArrayWriter();

    public HttpCharacterResponseWrapper(HttpServletResponse response){
        super(response);
        
    }
    
    @Override
    public PrintWriter getWriter() throws IOException{
        return new PrintWriter(ref_charArrayWriter);
    }
    
    public CharArrayWriter getCharArrayWriter() {
        return ref_charArrayWriter;
    }

}
