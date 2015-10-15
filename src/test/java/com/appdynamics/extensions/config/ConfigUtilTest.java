package com.appdynamics.extensions.config;


import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class ConfigUtilTest {

    @Test
    public void canLoadBaseConfigFile() throws FileNotFoundException {
        ConfigUtil<Configuration> configUtil = new ConfigUtil<Configuration>();
        Configuration configuration = configUtil.readConfig(this.getClass().getResource("/conf/config.yaml").getFile(),Configuration.class);
        Assert.assertTrue(configuration != null);
    }


}
