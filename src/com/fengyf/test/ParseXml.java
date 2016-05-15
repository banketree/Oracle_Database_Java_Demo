package com.fengyf.test;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class ParseXml {

	public static String getContent(String fStr) {
		//String fStr = "<?xml version='1.0' encoding='UTF-8'?>" +
		//			"<ROOT id='123456'>" +
		//				"<Name>AAA</Name>" + 
		//			"</ROOT>";
		String result = null;
		try {
			Document doc;
			doc = DocumentHelper.parseText(fStr);
			Element root = doc.getRootElement();
			Attribute testCmd = root.attribute("id");
			//System.out.println(testCmd.getName() + "：" + testCmd.getValue());
			result = testCmd.getName() + "：" + testCmd.getValue();
			Element eName = root.element("Name");
			//System.out.println("节点内容：" + eName.getTextTrim());
			result = result + "\n节点内容：" + eName.getTextTrim();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
