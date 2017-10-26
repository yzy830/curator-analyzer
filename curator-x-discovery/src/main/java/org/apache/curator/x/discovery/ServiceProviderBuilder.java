/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.curator.x.discovery;

import org.apache.curator.x.discovery.strategies.RoundRobinStrategy;
import java.util.concurrent.ThreadFactory;

public interface ServiceProviderBuilder<T>
{
    /**
     * Allocate a new service provider based on the current builder settings
     *
     * @return provider
     */
    public ServiceProvider<T> build();

    /**
     * required - set the name of the service to be provided
     *
     * @param serviceName the name of the service
     * @return this
     */
    public ServiceProviderBuilder<T> serviceName(String serviceName);

    /**
     * optional - set the provider strategy. The default is {@link RoundRobinStrategy}
     *
     * @param providerStrategy strategy to use
     * @return this
     */
    public ServiceProviderBuilder<T> providerStrategy(ProviderStrategy<T> providerStrategy);

    /**
     * optional - the thread factory to use for creating internal threads
     *
     * @param threadFactory factory to use
     * @return this
     */
    public ServiceProviderBuilder<T> threadFactory(ThreadFactory threadFactory);

    /**
     * Set the down instance policy
     *
     * @param downInstancePolicy new policy
     * @return this
     */
    public ServiceProviderBuilder<T> downInstancePolicy(DownInstancePolicy downInstancePolicy);

    /**
     * Add an instance filter. NOTE: this does not remove previously added filters. i.e.
     * a l;ist is created of all added filters. Filters are called in the order they were
     * added.
     *
     * @param filter filter to add
     * @return this
     */
    public ServiceProviderBuilder<T> additionalFilter(InstanceFilter<T> filter);
}
