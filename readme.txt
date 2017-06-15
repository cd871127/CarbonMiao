config.properties里面是配置,用文本编辑器可以打开.你可能需要修改fillNoVolume这个参数

fillNoVolume为true表示缺失volume时,用之前的值填充,如果为false表示不填充,显示no volume

这个参数主要针对重庆和广东的数据,因为他们缺少一些volume,

例如:
 2017年1月1日 price为30,volume为10
 2017年1月2日 price为30,缺少volume
 2017年1月3日 price为30,缺少volume
如果参数为true,那么excel里面会用1日的volume填充到2号和3号的数据里面,(那个网站上面就是这么干的,好像连续几日price相同,volume就是相同的)
如果参数为false,那么2,3号的volume显示为no volumes