package com.wrk.pcap.shared.core.config.build;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.info.ConditionalOnEnabledInfoContributor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(name = "org.springframework.boot.actuate.info.InfoContributor")
public class BuildInfoAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnEnabledInfoContributor("buildAndGit")
    public BuildAndGitInfoContributor buildAndGitInfoContributor(
            GitPropertiesReader reader,
            @Autowired(required = false)GitProperties git,
            @Autowired(required = false)BuildProperties build
            ){
        return new BuildAndGitInfoContributor(git,build,reader);
    }
    @Bean
    @ConditionalOnMissingBean
    public GitPropertiesReader gitPropertiesReader(){
        return new GitPropertiesReader();
    }
}
