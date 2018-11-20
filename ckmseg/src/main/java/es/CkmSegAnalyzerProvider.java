package es;

import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractIndexAnalyzerProvider;

// 分析器提供程序
public class CkmSegAnalyzerProvider extends AbstractIndexAnalyzerProvider<CkmSegAnalyzer> {
	private final CkmSegAnalyzer ckmSegAnalyzer;

	public CkmSegAnalyzerProvider(IndexSettings indexSettings, Environment environment,  String name, Settings settings) {
		super(indexSettings, name, settings);
		ckmSegAnalyzer = new CkmSegAnalyzer();
		// TODO Auto-generated constructor stub
	}

	@Override
	public CkmSegAnalyzer get() {
		// 返回自定义分析器
		return ckmSegAnalyzer;
	}

}
