package com.alsjava.challenge.memegenerator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by aluis on 12/14/19.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VersionAndBuild {

    private String name;
    private String group;
    private String version;
    private String build;
}
