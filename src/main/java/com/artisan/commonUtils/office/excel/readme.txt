选择Apache POI的原因：
1. Apache基金的支持. 
2. JExcel不支持xlsx格式而POI既支持xls格式又支持xlsx格式. 
3. Apache POI是基于流的处理,因此更适合大文件和要求更少的内存. 

Apache POI对处理Excel文件提供了强大的支持,并且能处理xls和xlsx格式的电子表格.


关于Apache POI一些重要的地方：
1.Apache POI包含适合Excel97-2007(.xls文件)的HSSF实现.
2.Apache POI XSSF实现用来处理Excel2007文件(.xlsx).
3.Apache POI HSSF和XSSF提供了读/写/修改Excel表格的机制.
4.Apache POI提供了XSSF的一个扩展SXSSF用来处理非常大的Excel工作单元.SXSSF API需要更少的内存,
   因此当处理非常大的电子表格同时堆内存又有限时,很合适使用.

有两种模式可供选择–事件模式和用户模式.
	-事件模式要求更少的内存,因为用tokens来读取Excel并处理.
	-用户模式更加面向对象并且容易使用,
因此在我们的示例中使用用户 模式.
Apache POI为额外的Excel特性提供了强大支持,例如处理公式,创建单元格样式–颜色,边框,字体,头部,脚部,数据验证,图像,超链接等.



Maven依赖：
 <!-- https://mvnrepository.com/artifact/org.apache.poi/poi -->
		<!-- POI核心依赖 -->  
		<dependency>
			<groupId>org.apache.poi</groupId>
			<artifactId>poi</artifactId>
			<version>${org.apache.poi}</version>
		</dependency>

		<!-- 为POI支持Office Open XML -->
		<dependency>
			<groupId>org.apache.poi</groupId>
			<artifactId>poi-ooxml</artifactId>
			<version>${org.apache.poi}</version>
		</dependency>
		<dependency>
			<groupId>org.apache.poi</groupId>
			<artifactId>poi-ooxml-schemas</artifactId>
			<version>${org.apache.poi}</version>
		</dependency>
		<!-- 支持Word文档的操作 -->
		<dependency>
			<groupId>org.apache.poi</groupId>
			<artifactId>poi-scratchpad</artifactId>
			<version>${org.apache.poi}</version>
		</dependency>
