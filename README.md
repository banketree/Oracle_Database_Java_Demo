# Oracle_Database_Demo
Oracle Database calls jar package execution java Demo

1. The need to be dependent on imported package
Import jar package
loadjava -r -f -o -user c ## test / nihao @ orcl * .jar
Remove jar package
dropjava -r -f -o -user c ## test / nihao @ orcl * .jar

2. Create the java class
create or replace and compile java source named ParseXml as
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
public class ParseXml {
public static String getContent (String fStr) {
String result = null;
try {
Document doc;
doc = DocumentHelper.parseText (fStr);
Element root = doc.getRootElement ();
Attribute testCmd = root.attribute ( "id");
//System.out.println (TestCmd.getName () + ":" + testCmd.getValue ());
result = testCmd.getName () + ":" + testCmd.getValue ();
Element eName = root.element ( "Name");
//System.out.println ( "Node content:" + eName.getTextTrim ());
result = result + "\ n node content:" + eName.getTextTrim ();

} Catch (Exception e) {
e.printStackTrace ();
}
return result;
}
}
/

3. Create function
create or replace function c ## test.getXmlContent (str in varchar2) return varchar2 as
  language java name 'ParseXml.getContent (java.lang.String) return oracle.sql.string';
/

4. Create a temporary table
create table TEST
(
  xml VARCHAR2 (4000)
);

/ *
<? Xml version = '1.0' encoding = 'UTF-8'?>
<ROOT id = '123456'>
  <Name> AAA </ Name>
</ ROOT>
* /

select t *, t.rowid from test t.;
select getXmlContent (t.xml) from test t;