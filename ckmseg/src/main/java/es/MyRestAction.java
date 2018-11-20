package es;

import org.apache.logging.log4j.LogManager;  
import org.apache.logging.log4j.Logger;  
import org.elasticsearch.action.admin.cluster.snapshots.status.TransportNodesSnapshotsStatus.Request;
import org.elasticsearch.action.admin.indices.get.GetIndexResponse;  
import org.elasticsearch.client.node.NodeClient;  
import org.elasticsearch.common.settings.Settings;  
import org.elasticsearch.common.xcontent.ToXContent;  
import org.elasticsearch.common.xcontent.XContentBuilder;  
import org.elasticsearch.common.xcontent.ToXContent.Params;
import org.elasticsearch.rest.*;  
import org.elasticsearch.common.inject.Inject;  
import org.elasticsearch.rest.action.RestBuilderListener;  
  







import com.trs.ckm.soap.CkmSoapException;
import com.trs.ckm.soap.SegDictWord;
import com.trs.ckm.soap.TrsCkmSoapClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.elasticsearch.rest.RestRequest.Method.GET; 


public class MyRestAction extends BaseRestHandler {
	private final static Logger LOGGER = LogManager.getLogger(MyRestAction.class);  
	  
    @Inject  
    public MyRestAction(Settings settings, RestController controller) {  
        super(settings);  
        controller.registerHandler(GET, "/ckmseg/{action}", this);  
        controller.registerHandler(GET, "/ckmseg", this);  
    }  
  
    
    private RestChannelConsumer createDoSomethingResponse(final RestRequest request, NodeClient client) {  
        return channel -> {  
            String action = request.param("action");  
            Something message = new Something();  
            message.action = action;  
            XContentBuilder builder = channel.newBuilder();  
            builder.startObject();  
            message.toXContent(builder, request);  
            builder.endObject();  
            channel.sendResponse(new BytesRestResponse(RestStatus.OK, builder));  
        };  
    }  
  
    private RestChannelConsumer createMessageResponse(RestRequest request) {  
        return channel -> {  
            Message message = new Message();  
            XContentBuilder builder = channel.newBuilder();  
            builder.startObject();  
            message.toXContent(builder, request);  
            builder.endObject();  
            channel.sendResponse(new BytesRestResponse(RestStatus.OK, builder));  
        };
}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected RestChannelConsumer prepareRequest(RestRequest request,
			NodeClient client) throws IOException {
		// TODO Auto-generated method stub
		LOGGER.info("Handle _jettro endpoint");  
		  
        String action = request.param("action");  
        String strInputAction = "exists";  
        if (action != null) {  
            LOGGER.info("do something action");  
            return createDoSomethingResponse(request, client);  
        } else {  
            return createMessageResponse(request);  
        }
	}
	
	class Message implements ToXContent {

		@Override
		public XContentBuilder toXContent(XContentBuilder builder, Params params)
				throws IOException {
			// TODO Auto-generated method stub
			return builder.field("message", "This is separate word plugin");  
		} 

	}
	
	class Something implements ToXContent {
		public String action;

		@Override
		public XContentBuilder toXContent(XContentBuilder builder, Params params)
				throws IOException {
			// TODO Auto-generated method stub
			StringBuffer strReturn = new StringBuffer();  
	        if (action != null)  
	        {  
	        	try {
	        	// 获取参数
        		Properties properties = new Properties();
       		    // 使用InPutStream流读取properties文件
//        		System.out.println(System.getProperty("user.dir") + File.separator + "plugins"+File.separator+"ckmseg"+File.separator+"application.properties");
	       		BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + File.separator + "plugins"+File.separator+"ckmseg"+File.separator+"application.properties"));
	       		properties.load(bufferedReader);
	       		// 获取key对应的value值
	       		String host = properties.getProperty("host");
	       		String user = properties.getProperty("user");
	       		String password = properties.getProperty("password");
	       		
	        	TrsCkmSoapClient client = new TrsCkmSoapClient(host, user, password);
//				TrsCkmSoapClient client = CkmDriver.getClient();
	        	SegDictWord[] segDictWords;

	        	segDictWords = client.PLOSegText(action.toString());
	        	for (int i = 0; (segDictWords != null && i < segDictWords.length); i++) {
//					System.out.println("关键词:" + segDictWords[i].getword());
//					System.out.println("类型:" + segDictWords[i].getcate());
//					System.out.println();
					strReturn.append("关键词:" + segDictWords[i].getword()
							+ ";  类型:" + segDictWords[i].getcate()  + ";    ");
				}

//	        	strReturn.append(action);
//	        	strReturn = "separate result : " + segDictWords.toString();  
	        	} catch (Exception e) {
	        		// TODO Auto-generated catch block
	        		e.printStackTrace();
	        	}
	        }  
	        else  
	        {  
//	            strReturn = "I can do anthing here. you order null" + "text:" + params.param("text");  
	        	strReturn.append("please input you words");
	        }  
	        return builder.field("words", strReturn.toString());  
		}

	}
}