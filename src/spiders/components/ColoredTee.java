package spiders.components;

import java.awt.Color;

import spiders.Spider;
import spiders.SpiderView;
import spiders.datas.Profile;
import spiders.datas.Statistics;
import spiders.datas.VisitedURL;

public class ColoredTee {
	
	/**
	 * Unix like colored tee subsystem
	 * 
	 */
	private SpiderView view;
	private Profile parameters;
	
	public ColoredTee(SpiderView view, Profile parameters) { 
		this.view = view; 
		this.parameters = parameters;
	}
	public void writeMessage(String message, boolean ln) {
		if (parameters.getUseConsole()) System.out.print(message+(ln ? "\n" : ""));
		if (view != null) view.appendMessage(message+(ln ? "\n" : ""),Color.BLACK);
	}
	
	public void writeInfos(String message, boolean ln) {
		if (parameters.getUseConsole()) System.err.print(message+(ln ? "\n" : ""));
		if (view != null) view.appendMessage(message+(ln ? "\n" : ""),Color.BLUE);
	}
	
	public void writeError(String message, boolean ln) {
		if (parameters.getUseConsole()) System.err.print(message+(ln ? "\n" : ""));
		if (view != null) view.appendMessage(message+(ln ? "\n" : ""), Color.RED);
	}		
	
	public void addURL(VisitedURL url, int size) {
		if (view == null) return;
		view.addURL(url);
		view.setStatus("Visited url : " + size);
	}
	
	public void showEnd(Boolean interrupted, Statistics statistics, String message) {
		if (view == null) return;
		view.updateCommandButtons(Spider.Status.STOPPED);
		view.setStatus("Visited url : " + statistics.getRead() + ". " +message);
		if (!interrupted) view.showDialog(message.replaceAll(" - ", "\n"));
		view.enableSite();
	}
}
