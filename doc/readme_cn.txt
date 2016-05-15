说明：Oracle数据库中调用jar包执行java Demo
1.需要将依赖包一并导入
导入jar包
loadjava -r -f -o -user c##test/nihao@orcl *.jar
删除jar包
dropjava -r -f -o -user c##test/nihao@orcl *.jar
2.创建java类
create or replace and compile java source named ParseXml as
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
public class ParseXml {
	public static String getContent(String fStr) {
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
/
3.创建函数
create or replace function c##test.getXmlContent(str in varchar2) return varchar2 as
  language java name 'ParseXml.getContent(java.lang.String) return oracle.sql.string';
/
4.创建临时表
create table TEST
(
  xml VARCHAR2(4000)
);
/*
<?xml version='1.0' encoding='UTF-8'?>
<ROOT id='123456'>
  <Name>AAA</Name>
</ROOT>
*/
select t.*,t.rowid from test t;
select getXmlContent(t.xml) from test t;
