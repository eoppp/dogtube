package jp.rainbowapps.dogtube.model;

public class Feed {

	public String xmlns;
	public String xmlns$media;
	public String xmlns$openSearch;
	public String xmlns$gd;
	public String xmlns$yt;
	public SimpleStringValue id;
	public SimpleStringValue updated;
	public Category[] category;
	public SimpleTypeValue title;
	public Link[] link;
	public Author[] author;
	public Generator generator;
	public SimpleIntegerValue openSearch$totalResults;
	public SimpleIntegerValue openSearch$startIndex;
	public SimpleIntegerValue openSearch$itemPerPage;
	public Entry[] entry;
}
