package com.dixonaws;

import com.amazonaws.services.lambda.runtime.*;
import com.github.javafaker.Faker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.LinkedHashMap;
import java.util.Map;


public class RandomUserApiJava implements RequestHandler<Object, String> {
    public static void main(String args[]) {
        RandomUserApiJava userApi=new RandomUserApiJava();

        LinkedHashMap input=new LinkedHashMap();
        input.put("message", "Hello World");

        Context context=new Context() {
            public String getAwsRequestId() {
                return null;
            }

            public String getLogGroupName() {
                return null;
            }

            public String getLogStreamName() {
                return null;
            }

            public String getFunctionName() {
                return null;
            }

            public String getFunctionVersion() {
                return null;
            }

            public String getInvokedFunctionArn() {
                return null;
            }

            public CognitoIdentity getIdentity() {
                return null;
            }

            public ClientContext getClientContext() {
                return null;
            }

            public int getRemainingTimeInMillis() {
                return 0;
            }

            public int getMemoryLimitInMB() {
                return 0;
            }

            public LambdaLogger getLogger() {
                return null;
            }
        };

        String strUser=userApi.handleRequest(input, context);

        System.out.println(strUser);

    } // main()

    public String handleRequest(Object input, Context context) {
        // this function accepts a JSON object with message: <String>
        LinkedHashMap mapInput = (LinkedHashMap) input;
        String strMessage = (String) mapInput.get("message");

        //context.getLogger().log("Input: " + input);

        return (generateUser());
    } // handleRequest()

    private String generateUser() {
        Faker faker = new Faker();

        String streetName = faker.address().streetName();
        String number = faker.address().buildingNumber();
        String city = faker.address().city();
        String country = faker.address().country();
        String firstName = faker.name().firstName(); // Emory
        String lastName = faker.name().lastName(); // Barton
        String state = faker.address().stateAbbr();
        String postCode = faker.address().zipCode();
        String homePhone = faker.phoneNumber().cellPhone();
        String workPhone = faker.phoneNumber().cellPhone();

        JSONObject jsonObject = new JSONObject();

        // putting data to JSONObject
        jsonObject.put("firstName", firstName);
        jsonObject.put("lastName", lastName);
        jsonObject.put("age", 25);

        // for address data, first create LinkedHashMap
        Map m = new LinkedHashMap(4);
        m.put("streetAddress", number + " " + streetName);
        m.put("city", city);
        m.put("state", state);
        m.put("postalCode", postCode);
        m.put("country", country);

        // putting address to JSONObject
        jsonObject.put("address", m);

        // for phone numbers, first create JSONArray
        JSONArray ja = new JSONArray();

        m = new LinkedHashMap(2);
        m.put("type", "home");
        m.put("number", homePhone);

        // adding map to list
        ja.add(m);

        m = new LinkedHashMap(2);
        m.put("type", "work");
        m.put("number", workPhone);

        // adding map to list
        ja.add(m);

        // putting phoneNumbers to JSONObject
        jsonObject.put("phoneNumbers", ja);

        return(jsonObject.toJSONString());

    } // generateUser()


}
