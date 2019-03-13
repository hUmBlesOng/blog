package com.hs.blog.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * TODO: MyBatis逆向工程生成类
 *
 * @author 83998
 * @date 2019/3/4 16:39
 */
public class MybatisGenerator {

    private static final Logger log = LoggerFactory.getLogger(MybatisGenerator.class);

    public static void main(String[] args) throws Exception {
        String today = "2019-03-04";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = sdf.parse(today);
        Date d = new Date();

        if (d.getTime() > now.getTime() + 1000 * 60 * 60 * 24) {
            log.info("——————未成成功运行——————");
            log.info("——————未成成功运行——————");
            log.info("本程序具有破坏作用，应该只运行一次，如果必须要再运行，需要修改today变量为今天，如:" +
                    sdf.format(new Date()));
            return;
        }

        if (false)
            return;
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        InputStream is = MybatisGenerator.class.getClassLoader().getResource("generatorConfig.xml").openStream();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(is);
        is.close();
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

        log.info("生成代码成功，只能执行一次，以后执行会覆盖掉mapper,pojo,xml 等文件上做的修改");
    }
}
