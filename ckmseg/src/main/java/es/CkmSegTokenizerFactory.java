package es;

import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenizerFactory;

// 分词器工厂
public class CkmSegTokenizerFactory  extends AbstractTokenizerFactory {

	

	public CkmSegTokenizerFactory(IndexSettings indexSettings, Environment environment, String ignored, Settings settings) {
		super(indexSettings, ignored, settings);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Tokenizer create() {
		// 返回自定义Tokenizer
		return new CkmSegTokenizer();
	}

}
