#WSO2 Message Broker

Welcome to the WSO2 Message broker.

WSO2 MB is a lightweight and easy-to-use Open Source Distributed Message Brokering
Server (MB) which is available under the Apache Software License v2.0.

This is based on the revolutionary WSO2 Carbon [Middleware a' la carte]
framework. All the major features have been developed as pluggable Carbon
components.

<h2>Building the Distribution</h2>
<ol>
<li> Clone "hamming-release-poc" branch from "carbon-kernel" repository from https://github.com/wso2/carbon-kernel : https://github.com/wso2/carbon-kernel/tree/hamming-release-poc/ </li>
<li> Go to the "carbon-kernel" folder and then into the "modules" folder. </li>
<li> Run "mvn clean install" in the following folder : "carbon-hazelcast" </li>
<li> Clone this repository(https://github.com/wso2/product-mb/tree/c5-migration) and run "mvn clean install". </li>
<li> The distribution will be available at "product-mb/product/target" folder. </li>
</ol>

(c) 2016, WSO2 Inc.

