package org.apache.camel.example;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.server.handler.ErrorHandler;

public class CustomErrorHandler extends ErrorHandler{
	boolean disableStacks = true;
	@Override
    protected void writeErrorPageBody(HttpServletRequest request, Writer writer, int code, String message, boolean showStacks)
            throws IOException
        {
            String uri = request.getRequestURI();

            writeErrorPageMessage(request, writer, code, message, uri);
            if (showStacks && !disableStacks)
                writeErrorPageStacks(request, writer);


        }

}
