package org.apache.camel.example.processor;

import io.netty.handler.codec.http.multipart.*;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.netty4.http.NettyHttpMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class UploadProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        // get netty message
        NettyHttpMessage nettyHttpMessage = exchange.getIn(NettyHttpMessage.class);
        // use HttpPostRequestDecoder to extract form data
        HttpPostRequestDecoder postRequest = new HttpPostRequestDecoder(nettyHttpMessage.getHttpRequest());
        getHttpDataAttributes(postRequest,exchange);
    }
    
    public void getHttpDataAttributes(HttpPostRequestDecoder request, Exchange exchange) {
        try {
            for (InterfaceHttpData part : request.getBodyHttpDatas()) {
                if (part instanceof MixedAttribute) {
                    Attribute attribute = (MixedAttribute) part;
                    LOGGER.info(String.format("Found part with key: %s and value: %s ", attribute.getName(), attribute.getValue()));
                } else if (part instanceof MixedFileUpload) {
                    MixedFileUpload attribute = (MixedFileUpload) part;
                    LOGGER.info(String.format("File Name: %s ", attribute.getFilename()));
                    LOGGER.info(String.format("Data: %s ", attribute.getString()));
                    exchange.getIn().setBody(attribute.getString());
                }
            }
        } catch (IOException e) {
            String errorMsg = String.format("Cannot parse request:");
            LOGGER.error(errorMsg,e);
        }
    }
}
