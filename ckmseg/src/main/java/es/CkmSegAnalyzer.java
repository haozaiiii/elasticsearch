package es;


import org.apache.lucene.analysis.Analyzer;

// �ִ���
public class CkmSegAnalyzer extends Analyzer {

	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		// TODO Auto-generated method stub
		return new TokenStreamComponents(new CkmSegTokenizer());
	}

}
