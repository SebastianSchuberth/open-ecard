/****************************************************************************
 * Copyright (C) 2013-2014 ecsec GmbH.
 * All rights reserved.
 * Contact: ecsec GmbH (info@ecsec.de)
 *
 * This file is part of the Open eCard App.
 *
 * GNU General Public License Usage
 * This file may be used under the terms of the GNU General Public
 * License version 3.0 as published by the Free Software Foundation
 * and appearing in the file LICENSE.GPL included in the packaging of
 * this file. Please review the following information to ensure the
 * GNU General Public License version 3.0 requirements will be met:
 * http://www.gnu.org/copyleft/gpl.html.
 *
 * Other Usage
 * Alternatively, this file may be used in accordance with the terms
 * and conditions contained in a signed written agreement between
 * you and ecsec GmbH.
 *
 ***************************************************************************/

package org.openecard.addon.manifest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.openecard.common.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Tobias Wich <tobias.wich@ecsec.de>
 * @author Dirk Petrautzki <petrautzki@hs-coburg.de>
 */
@XmlRootElement(name = "AddonSpecification")
@XmlType(propOrder = { "id", "version", "license", "licenseText", "localizedName", "localizedDescription", "about", "logo",
	"configDescription", "bindingActions", "applicationActions", "ifdActions", "salActions" })
@XmlAccessorType(XmlAccessType.FIELD)
public class AddonSpecification {

    private static final Logger logger = LoggerFactory.getLogger(AddonSpecification.class);

    @XmlElement(name = "ID", required = true)
    private String id;
    @XmlElement(name = "Logo", required = true)
    private String logo;
    //private byte[] logoBytes;
    @XmlElement(name = "Version", required = true)
    private String version;
    @XmlElement(name = "License", required = true)
    private String license;
    @XmlElement(name = "LicenseText", required = false)
    private final List<LocalizedString> licenseText = new ArrayList<>();
    @XmlElement(name = "ConfigDescription", required = true)
    private Configuration configDescription;
    @XmlElement(name = "LocalizedName", type = LocalizedString.class, required = false)
    private final List<LocalizedString> localizedName = new ArrayList<>();
    @XmlElement(name = "LocalizedDescription", type = LocalizedString.class, required = false)
    private final List<LocalizedString> localizedDescription = new ArrayList<>();
    @XmlElement(name = "About", type = LocalizedString.class, required = false)
    private final List<LocalizedString> about = new ArrayList<>();
    @XmlElement(name = "AppExtensionSpecification", type = AppExtensionSpecification.class, required = false)
    @XmlElementWrapper(name = "ApplicationActions", required = false)
    private final ArrayList<AppExtensionSpecification> applicationActions = new ArrayList<>();
    @XmlElementWrapper(name = "BindingActions", required = false)
    @XmlElement(name = "AppPluginSpecification", type = AppPluginSpecification.class, required = false)
    private final ArrayList<AppPluginSpecification> bindingActions = new ArrayList<>();
    @XmlElementWrapper(name = "IFDActions", required = false)
    @XmlElement(name = "ProtocolPluginSpecification", type = ProtocolPluginSpecification.class, required = false)
    private final ArrayList<ProtocolPluginSpecification> ifdActions = new ArrayList<>();
    @XmlElementWrapper(name = "SALActions", required = false)
    @XmlElement(name = "ProtocolPluginSpecification", type = ProtocolPluginSpecification.class, required = false)
    private final ArrayList<ProtocolPluginSpecification> salActions = new ArrayList<>();

    public String getId() {
	return id;
    }

    public List<LocalizedString> getLocalizedName() {
	return localizedName;
    }

    public String getLocalizedName(String languageCode) {
	String fallback = "";
	for (LocalizedString s : localizedName) {
	    if (s.getLang().equalsIgnoreCase(languageCode)) {
		return s.getValue();
	    } else if (s.getLang().equalsIgnoreCase("EN")) {
		fallback = s.getValue();
	    }
	}
	return fallback;
    }

    public List<LocalizedString> getLocalizedDescription() {
	return localizedDescription;
    }

    public String getLocalizedDescription(String languageCode) {
	String fallback = "";
	for (LocalizedString s : localizedDescription) {
	    if (s.getLang().equalsIgnoreCase(languageCode)) {
		return s.getValue();
	    } else if (s.getLang().equalsIgnoreCase("EN")) {
		fallback = s.getValue();
	    }
	}
	return fallback;
    }

    public String getLogo() {
	return logo;
    }

    public String getVersion() {
	return version;
    }

    public List<LocalizedString> getAbout() {
	return about;
    }

    public String getAbout(String languageCode) {
	String fallback = "";
	for (LocalizedString s : about) {
	    if (s.getLang().equalsIgnoreCase(languageCode)) {
		return s.getValue();
	    } else if (s.getLang().equalsIgnoreCase("EN")) {
		fallback = s.getValue();
	    }
	}
	return fallback;
    }

    public String getLicense() {
	return license;
    }

    public List<LocalizedString> getLicenseText() {
	return licenseText;
    }

    public String getLicenseText(String languageCode) {
	String fallback = "";
	for (LocalizedString s : licenseText) {
	    if (s.getLang().equalsIgnoreCase(languageCode)) {
		return s.getValue();
	    } else if (s.getLang().equalsIgnoreCase("EN")) {
		fallback = s.getValue();
	    }
	}
	return fallback;
    }

    public ArrayList<AppPluginSpecification> getBindingActions() {
	return bindingActions;
    }

    public ArrayList<ProtocolPluginSpecification> getIfdActions() {
	return ifdActions;
    }

    public ArrayList<AppExtensionSpecification> getApplicationActions() {
	return applicationActions;
    }

    public ArrayList<ProtocolPluginSpecification> getSalActions() {
	return salActions;
    }


    public Configuration getConfigDescription() {
	return configDescription;
    }

    public void setId(String id) {
	this.id = id;
    }

    public void setLogo(String logo) {
	this.logo = logo;
	logger.debug("LogoFile: " + logo);
    }

    /**
     * Get a byte array containing the logo.
     * <br />
     * Note: This method creates always a new input stream and does not store the byte array internally.
     *
     * @return A byte array containing the logo bytes or null if no logo is present or an error occurred.
     */
    public byte[] getLogoBytes() {
	if (logo != null && !logo.isEmpty()) {
	    try {
		// TODO security checks and maybe modified loading
		InputStream logoStream = FileUtils.resolveResourceAsStream(AddonSpecification.class, logo);
		return FileUtils.toByteArray(logoStream);
	    } catch (FileNotFoundException e) {
		logger.error("Logo file couldn't be found.", e);
		return null;
	    } catch (IOException | NullPointerException e) {
		logger.error("Logo file couldn't be read.", e);
		return null;
	    }
	}
	return null;
    }

    public void setVersion(String version) {
	this.version = version;
    }

    public void setLicense(String license) {
	this.license = license;
    }

    public void setConfigDescription(Configuration configDescriptionNew) {
	this.configDescription = configDescriptionNew;
    }

    public AppPluginSpecification searchByResourceName(String resourceName) {
	for (AppPluginSpecification desc : bindingActions) {
	    if (resourceName.equals(desc.getResourceName())) {
		return desc;
	    }
	}
	return null;
    }

    public AppExtensionSpecification searchByActionId(String id) {
	for (AppExtensionSpecification desc : applicationActions) {
	    if (desc.getId().equals(id)) {
		return desc;
	    }
	}
	return null;
    }

    public ProtocolPluginSpecification searchIFDActionByURI(String uri) {
	for (ProtocolPluginSpecification desc : ifdActions) {
	    if (desc.getUri().equals(uri)) {
		return desc;
	    }
	}
	return null;
    }

    public ProtocolPluginSpecification searchSALActionByURI(String uri) {
	for (ProtocolPluginSpecification desc : salActions) {
	    if (desc.getUri().equals(uri)) {
		return desc;
	    }
	}
	return null;
    }
}
