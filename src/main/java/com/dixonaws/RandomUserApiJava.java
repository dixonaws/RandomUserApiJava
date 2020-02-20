package com.dixonaws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.LinkedHashMap;

public class RandomUserApiJava implements RequestHandler<Object, String> {

    public String handleRequest(Object input, Context context) {
        // this function accepts a JSON object with message: <String>
        LinkedHashMap mapInput = (LinkedHashMap) input;
        String strMessage = (String) mapInput.get("message");

        context.getLogger().log("Input: " + input);

        return (strMessage);
    }


}
