package test;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;

import com.trs.ckm.soap.CkmSoapException;
import com.trs.ckm.soap.SegDictWord;
import com.trs.ckm.soap.TrsCkmSoapClient;

import es.CkmSegAnalyzer;
import es.CkmSegTokenizer;

public class CkmSegTests {
	@Test
    public void testAnalyzer() throws Exception {
        CkmSegAnalyzer analyzer = new CkmSegAnalyzer();
        TokenStream ts = analyzer.tokenStream("text", "我爱北京 天安门 故宫 博物院");
        CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);
        ts.reset();
        while (ts.incrementToken()) {
            System.out.println(term.toString());
        }
        ts.end();
        ts.close();
    }
	
	@Test
	public void test01(){
		TrsCkmSoapClient client = new TrsCkmSoapClient("http://192.168.105.30:8000", "admin", "trsadmin");
		SegDictWord[] segDictWords;
		try {
			segDictWords = client.PLOSegText("我爱北京");
    	for (int i = 0; (segDictWords != null && i < segDictWords.length); i++) {
    		System.out.println(segDictWords[i].getword());
    	}
		} catch (CkmSoapException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
