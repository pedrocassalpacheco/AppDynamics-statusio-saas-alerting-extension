package com.appdynamics.extensions.service.appd.hrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "policy-violations")
public class PolicyViolationWrapper {

    @XmlElement(name="policy-violation")
    List<PolicyViolation> policyViolations;

    public List<PolicyViolation> getPolicyViolations() {
        return policyViolations;
    }

    public void setPolicyViolations(List<PolicyViolation> policyViolations) {
        this.policyViolations = policyViolations;
    }
}
