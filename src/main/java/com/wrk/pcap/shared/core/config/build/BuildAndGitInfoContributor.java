package com.wrk.pcap.shared.core.config.build;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class BuildAndGitInfoContributor implements InfoContributor {
    private final GitProperties gitProperties;
    private final BuildProperties buildProperties;
    private final GitPropertiesReader gitPropertiesReader;

    public BuildAndGitInfoContributor(@Autowired(required = false) GitProperties gitProperties,
                                      @Autowired(required = false) BuildProperties buildProperties,
                                      GitPropertiesReader gitPropertiesReader) {
        this.gitProperties = gitProperties;
        this.buildProperties = buildProperties;
        this.gitPropertiesReader = gitPropertiesReader;
    }

    @Override
    public void contribute(Info.Builder builder) {
        Map<String,Object> gitMap = new LinkedHashMap<>();
        Map<String,Object> buildMap = new LinkedHashMap<>();
        if(gitProperties != null){
            gitMap.put("branch", gitProperties.getBranch());
            gitMap.put("commitId", gitProperties.getCommitId());
            gitMap.put("commitTime",gitProperties.getCommitTime());
            gitMap.put("commitMessage",gitProperties.get("commit.message.full"));
            gitMap.put("authorEmail",gitProperties.get("commit.user.email"));
            gitMap.put("authorName",gitProperties.get("commit.user.name"));
        }
        else {
            Properties p = gitPropertiesReader.read();
            if(p != null){
                gitMap.put("branch", p.getProperty("git.branch"));
                gitMap.put("commit", p.getProperty("git.commit.id"));
                gitMap.put("commitTime",p.getProperty("git.commit.time"));
                gitMap.put("commitAbbrev", p.getProperty("git.commit.id.abbrev"));
                gitMap.put("message", p.getProperty("git.commit.message.full"));
                gitMap.put("author", p.getProperty("git.commit.user.name"));
            }
        }
        if(buildProperties != null){
            buildMap.put("branch", buildProperties.getArtifact());
            buildMap.put("name", buildProperties.getGroup()+":"+buildProperties.getArtifact());
            buildMap.put("version", buildProperties.getVersion());
            buildMap.put("time", buildProperties.getTime());
        }
        if(!gitMap.isEmpty()){ builder.withDetail("git", gitMap); }
        if(!buildMap.isEmpty()){ builder.withDetail("build", buildMap); }
    }

}
