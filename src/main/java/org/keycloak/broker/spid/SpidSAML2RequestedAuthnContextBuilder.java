/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.keycloak.broker.spid;

import org.keycloak.dom.saml.v2.protocol.AuthnContextComparisonType;
import org.keycloak.dom.saml.v2.protocol.RequestedAuthnContextType;

import java.util.ArrayList;
import java.util.List;

public class SpidSAML2RequestedAuthnContextBuilder {
    private final RequestedAuthnContextType requestedAuthnContextType;
    private AuthnContextComparisonType comparison;
    private List<String> requestedAuthnContextClassRefList;
    private List<String> requestedAuthnContextDeclRefList;

    public SpidSAML2RequestedAuthnContextBuilder() {
        this.requestedAuthnContextType = new RequestedAuthnContextType();
        this.requestedAuthnContextClassRefList = new ArrayList<String>();
        this.requestedAuthnContextDeclRefList = new ArrayList<String>();
    }

    public SpidSAML2RequestedAuthnContextBuilder setComparison(AuthnContextComparisonType comparison) {
        this.comparison = comparison;
        return this;
    }

    public SpidSAML2RequestedAuthnContextBuilder addAuthnContextClassRef(String authnContextClassRef) {
        this.requestedAuthnContextClassRefList.add(authnContextClassRef);
        return this;
    }

    public SpidSAML2RequestedAuthnContextBuilder addAuthnContextDeclRef(String authnContextDeclRef) {
        this.requestedAuthnContextDeclRefList.add(authnContextDeclRef);
        return this;
    }

    public RequestedAuthnContextType build() {
        if (this.comparison != null)
            this.requestedAuthnContextType.setComparison(this.comparison);

        for (String requestedAuthnContextClassRef: this.requestedAuthnContextClassRefList)
            if (requestedAuthnContextClassRef != null && requestedAuthnContextClassRef.length() > 0)
                this.requestedAuthnContextType.addAuthnContextClassRef(requestedAuthnContextClassRef);

        for (String requestedAuthnContextDeclRef: this.requestedAuthnContextDeclRefList)
            if (requestedAuthnContextDeclRef != null && requestedAuthnContextDeclRef.length() > 0)
                this.requestedAuthnContextType.addAuthnContextDeclRef(requestedAuthnContextDeclRef);

        return this.requestedAuthnContextType;
    }
}