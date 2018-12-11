package katok.Core.API;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

public class VkApiHandler {
    private HttpClient client = HttpClientBuilder.create().build();
    private URIBuilder builder;
    private HttpGet request;
    private int messageID;
    private String access_token = "access_token";
    private String access_tokenValue = "5a19debb7dc868d54622a4de56e17f6aee489c5c876e98e317ba84cfdffd03bb4e487cf40ee99b37542f1";
    private String user_id = "user_id";
    private String message = "message";
    private String v = "v";
    private String v_value = "5.73";
    private String peer_id = "peer_id";

    public int getMessageID() {
        return messageID;
    }

    public void sendMessage(int recepient, String messageText) throws IOException, URISyntaxException {
        builder = new URIBuilder("https://api.vk.com/method/messages.send?");
        builder.setParameter(access_token, access_tokenValue)
                .setParameter(user_id, String.valueOf(recepient))
                .setParameter(message, messageText)
                .setParameter(v, v_value);
        request = new HttpGet(builder.build());
        HttpResponse response = client.execute(request);
        messageID = Integer.parseInt(EntityUtils.toString(response.getEntity()).replaceAll("\\D", ""));
    }

    public String getMessageText(int messageID) throws URISyntaxException, IOException {
        builder = new URIBuilder("https://api.vk.com/method/messages.getById?");
        builder.setParameter(access_token, access_tokenValue)
                .setParameter("message_ids", String.valueOf(messageID))
                .setParameter(v, v_value);
        request = new HttpGet(builder.build());
        HttpResponse response = client.execute(request);
        JSONObject vkMessage = new JSONObject(EntityUtils.toString(response.getEntity()));
        JSONObject responceObj = vkMessage.getJSONObject("response");
        JSONArray entities = responceObj.getJSONArray("items");
        JSONObject text = entities.getJSONObject(0);
        return text.get("body").toString();
    }

}
