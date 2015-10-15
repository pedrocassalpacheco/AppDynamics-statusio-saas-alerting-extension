package com.appdynamics.extensions;

import com.appdynamics.extensions.config.ConfigUtil;
import com.appdynamics.extensions.config.Configuration;
import com.appdynamics.extensions.service.appd.ServiceImpl;
import com.google.common.base.Strings;
import com.singularity.ee.agent.systemagent.api.AManagedMonitor;
import com.singularity.ee.agent.systemagent.api.TaskExecutionContext;
import com.singularity.ee.agent.systemagent.api.TaskOutput;
import com.singularity.ee.agent.systemagent.api.exception.TaskExecutionException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
             
public class StatusioAlertExtension extends AManagedMonitor {

    private static final Logger logger = Logger.getLogger(StatusioAlertExtension.class);
    private ExecutorService threadPool;

    public static final String CONFIG_ARG = "config-file";

    //To load the config files
    final static ConfigUtil<Configuration> configUtil = new ConfigUtil<Configuration>();

    public StatusioAlertExtension() {
        String msg = "Using Monitor Version [" + getImplementationVersion() + "]";
        logger.info(msg);
        System.out.println(msg);
    }

    @Override
    public TaskOutput execute(Map<String, String> taskArgs, TaskExecutionContext taskExecutionContext) throws TaskExecutionException {
        if (taskArgs != null) {
            logger.info("Starting the Status.io Extension");
            logger.debug("Task Arguments Passed ::" + taskArgs);
            
            String configFilename = getConfigFilename(taskArgs.get(CONFIG_ARG));
            try {
                // load the base configuration
                Configuration config = configUtil.readConfig(configFilename, Configuration.class);
                
                // Execute task
                CustomerTask customerTask = new CustomerTask(config,new ServiceImpl());
                customerTask.execute();
                
                return new TaskOutput("Statusio SaaS Alerting extension completed successfully.");
                
            } catch (FileNotFoundException e) {
                logger.error("Config file not found." + e);
            } catch (Exception e) {
                logger.error("Scheduled task failed." + e);
            }
        }
        throw new TaskExecutionException("Statusio SaaS Alerting extension completed with failures.");
    }


    /**
     * Returns a config file name,
     * @param filename
     * @return String
     */
    private String getConfigFilename(String filename) {
        if(filename == null){
            return "";
        }
        //for absolute paths
        if(new File(filename).exists()){
            return filename;
        }
        //for relative paths
        File jarPath = PathResolver.resolveDirectory(AManagedMonitor.class);
        String configFileName = "";
        if(!Strings.isNullOrEmpty(filename)){
            configFileName = jarPath + File.separator + filename;
        }
        return configFileName;
    }

    public static String getImplementationVersion() {
        return StatusioAlertExtension.class.getPackage().getImplementationTitle();
    }   
}
