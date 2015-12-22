package spiders.datas;

public class Statistics {

	private Integer URLUsed = 0;
	private Integer URLRead = 0;
	private Integer URLError = 0;
	private Integer MaxURLPerPage = 0;
	private Integer MaxIMGPerPage = 0;
	
	public Statistics() {}
	
	public Integer getUsed() { return URLUsed; }
	public Integer getRead() { return URLRead; }
	public Integer getError() { return URLError; }
	
	public Integer getMaxURLPerPage() { return MaxURLPerPage; }
	public Integer getMaxIMGPerPage() { return MaxIMGPerPage; }
	
	public void addUsed() {URLUsed++;}
	public void addRead() {URLRead++;}
	public void addError() {URLError++;}
	public void clear() {URLUsed = URLRead = URLError = 0; }
	
	public void setMaxURLPerPage(Integer nLinks) {MaxURLPerPage = nLinks;}
	public void setMaxIMGPerPage(Integer nImages) {MaxIMGPerPage = nImages;}
}
