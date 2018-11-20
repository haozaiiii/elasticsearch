package es;

import java.io.IOException;
import java.io.Reader;


import com.trs.ckm.soap.CkmSoapException;
import com.trs.ckm.soap.SegDictWord;
import com.trs.ckm.soap.TrsCkmSoapClient;


public class CkmSegmenter {
	boolean seg = false;
	
	private Reader input;
	
	private int i=0;
	
	SegDictWord[] segDictWords;
	
	public void CkmSegmenter(Reader input){
		this.input = input;
		
	}
	
	public String next(){
		i++;
		return segDictWords[i-1].getword();
	}
	/**
	 * 分词
	 */
	public int seg(Reader input){
		StringBuilder buffer = new StringBuilder();
		TrsCkmSoapClient client = new TrsCkmSoapClient("http://192.168.105.30:8000", "admin", "trsadmin");

		int ci;
		try {
			ci = input.read();
			while(ci != -1){
				buffer.append((char)ci);
				ci = input.read();
			}
			segDictWords = client.PLOSegText(String.valueOf(buffer.toString()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CkmSoapException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return segDictWords.length;
    	
		
	}
	
	/**
     * 重置分词器到初始状态
     * @param input
     */
	public synchronized int reset(Reader input) {
		this.input = input;
		return this.seg(input);
	}

}
