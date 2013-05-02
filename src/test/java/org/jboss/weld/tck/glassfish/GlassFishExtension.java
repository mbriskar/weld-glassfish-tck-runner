/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package org.jboss.weld.tck.glassfish;

import org.jboss.arquillian.container.spi.client.container.DeploymentExceptionTransformer;
import org.jboss.arquillian.core.spi.LoadableExtension;
//import org.jboss.as.arquillian.container.ExceptionTransformer;

/**
 * Registers the exception transformer to properly identify deployment failures.
 *
 * @author J J Snyder (j.j.snyder@oracle.com)
 */
public class GlassFishExtension implements LoadableExtension {

    private static final String GLASSFISH_CLIENTUTILS_CLASS = "org.jboss.arquillian.container.glassfish.clientutils.GlassFishClientUtil";
   
    public void register(ExtensionBuilder builder) {

        if (Validate.classExists(GLASSFISH_CLIENTUTILS_CLASS)) {
            builder.service(DeploymentExceptionTransformer.class, GlassFishDeploymentExceptionTransformer.class);
//            builder.service(ExceptionTransformer.class, GlassFishDeploymentExceptionTransformer.class);
            // Override the default NOOP exception transformer
//            builder.override(DeploymentExceptionTransformer.class,
//                             ExceptionTransformer.class,
//                             GlassFishDeploymentExceptionTransformer.class);
            // Observe container start and check EE resources
            builder.observer(GlassFishResourceManager.class);
        }
    }

}
