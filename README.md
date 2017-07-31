# XLD Pause Plugin 

[![Build Status](https://travis-ci.org/xebialabs-community/xld-pause-plugin.svg?branch=master)](https://travis-ci.org/xebialabs-community/xld-pause-plugin)

## Preface ##

This document describes the functionality provided by the XL Deploy Pause Plugin.

If you are using XL Deploy 4.5+, please consider using XL Deploy's xml rules to add pause steps to the deployment plan. This is the prefered way to realise use cases that require the pausing of a deployment. Refer to [Writing Xml Rules](https://docs.xebialabs.com/xl-deploy/how-to/writing-xml-rules.html) and [Pause Step Reference](#configuration-via-xl-deploy-rules) for more details.


## Overview ##

The XLD Pause plugin allows for the automatic addition of a pause step to the deployment plan depending on the __udm.Environment__. If the plugin is enabled for the environment a pause step is added, at a predefined order, for all deployments targeted to the environment.

## Features ##

* Add pause step for all deployements to environment.
* Configuration order of the step
* Enable, disable plugin per enviroment
* Control whether to add pause step for NOOP
* Control whether to add pause step of undeployment
* Ability to be used with XL Rules.

## Requirements ##

* **XLD Server** 4.5+
		

## Installation

Place the 'xld-pause-plugin-&lt;version&gt;.jar' file into your SERVER_HOME/plugins directory.

## Configuration via __udm.Environment__ settings.

The __udm.Environment__ is modified by the plugin to have the following additional properties to control its behavior:

| Property | Description | Default |
|----------|-------------|---------|
| pausable | Enable/disable plugin for the environment | false |
| pauseOrder | Order at which to insert the pause step | 85 |
| pauseOnNoop | Whether to add the pause step when the plan only contains NOOP operation deployeds | false |
| pauseOnUndeploy | Whether to add the pause step when the plan only contains DESTROY operation deployeds | false |

## Configuration via XL Deploy Rules. 

The plugin defines a `pause` step that can be used with XL Deploy's rule system.

| Property | Description | Caculated |
|----------|-------------|---------|
| order | Order at which to insert the pause step | Yes. Defaults to 50.|
| description | Description for the pause step. | Yes|

### Example

A fully-specified step created with an XML rule.

    <pause>
        <description expression="true">"Pause the plan for the %s deployed " % (deployed.name)</description>
        <order>70</order>        
    </pause>

