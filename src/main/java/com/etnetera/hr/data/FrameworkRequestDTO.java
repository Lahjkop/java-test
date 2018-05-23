package com.etnetera.hr.data;

import java.time.LocalDate;

import com.etnetera.hr.rest.HypeLevel;

public class FrameworkRequestDTO {

    private Long id;

    private String name;

    private String version;

    private HypeLevel hypeLevel;

    private String nameToSet;

    private String versionToUpdate;

    private HypeLevel hypeLevelToSet;

    private LocalDate depreciationDateToSet;

    public FrameworkRequestDTO(final Long id, final String name, final String version, final HypeLevel hypeLevel) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.hypeLevel = hypeLevel;
    }

    public FrameworkRequestDTO(final Long id, final String name, final String version, final HypeLevel hypeLevel, final String nameToSet,
            final String versionToUpdate,
            final HypeLevel hypeLevelToSet, final LocalDate depreciationDateToSet) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.hypeLevel = hypeLevel;
        this.nameToSet = nameToSet;
        this.versionToUpdate = versionToUpdate;
        this.hypeLevelToSet = hypeLevelToSet;
        this.depreciationDateToSet = depreciationDateToSet;
    }

    public FrameworkRequestDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(final String version) {
        this.version = version;
    }

    public HypeLevel getHypeLevel() {
        return hypeLevel;
    }

    public void setHypeLevel(final HypeLevel hypeLevel) {
        this.hypeLevel = hypeLevel;
    }

    public String getNameToSet() {
        return nameToSet;
    }

    public void setNameToSet(final String nameToSet) {
        this.nameToSet = nameToSet;
    }

    public String getVersionToUpdate() {
        return versionToUpdate;
    }

    public void setVersionToUpdate(final String versionToUpdate) {
        this.versionToUpdate = versionToUpdate;
    }

    public HypeLevel getHypeLevelToSet() {
        return hypeLevelToSet;
    }

    public void setHypeLevelToSet(final HypeLevel hypeLevelToSet) {
        this.hypeLevelToSet = hypeLevelToSet;
    }

    public LocalDate getDepreciationDateToSet() {
        return depreciationDateToSet;
    }

    public void setDepreciationDateToSet(final LocalDate depreciationDateToSet) {
        this.depreciationDateToSet = depreciationDateToSet;
    }
}
