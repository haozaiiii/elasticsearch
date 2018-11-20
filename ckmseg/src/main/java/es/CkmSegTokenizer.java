package es;

import java.io.IOException;

import javax.swing.text.Segment;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;

import com.trs.ckm.soap.CkmSoapException;
import com.trs.ckm.soap.SegDictWord;
import com.trs.ckm.soap.TrsCkmSoapClient;

// �ִ���
public class CkmSegTokenizer extends Tokenizer {
	private final StringBuilder buffer = new StringBuilder();
	//��Ԫ�ı�����
	private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
	//��Ԫλ������
	private final OffsetAttribute offsetAtt = addAttribute(OffsetAttribute.class);
	
	private final static String PUNCTION = " -()/";
	
	private int tokenStart = 0, tokenEnd = 0;
	
	private int suffixOffset = 0;
	
	private CkmSegmenter ckmsegment;
	
	private int seglength;
	
	private int temp = 0;
	
	public CkmSegTokenizer(){
		ckmsegment = new CkmSegmenter();
	}

	@Override
	public final boolean incrementToken() throws IOException {
		
		// ʵ���Լ��ķִʳ���
		clearAttributes();

		if(temp<seglength){
			String word = ckmsegment.next();
			//���ô�Ԫ�ı�
			termAtt.append(word);
			//���ô�Ԫλ��
			tokenEnd += word.length();
			offsetAtt.setOffset(correctOffset(tokenStart),correctOffset(tokenEnd));
			tokenStart = tokenEnd;
			temp++;
			return true;
		}
		
		return false;
	}
	
	@Override
    public final void end() {
        final int finalOffset = correctOffset(suffixOffset);
        this.offsetAtt.setOffset(finalOffset, finalOffset);
    }

	
	@Override
	public void reset() throws IOException {
		super.reset();
		seglength = ckmsegment.reset(input);
		
	}
    /*@Override
    public void reset() throws IOException {
        super.reset();
        tokenStart = tokenEnd = 0;
    }*/

}
