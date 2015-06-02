[![Build Status](https://travis-ci.org/NicolasGeraud/xld-pause-plugin.svg?branch=master)](https://travis-ci.org/NicolasGeraud/xld-pause-plugin)
## Description

An XL Deploy plugin that generate pause steps.

## Installation

Place the 'xld-pause-plugin-&lt;version&gt;.jar' file into your SERVER_HOME/plugins directory.

## Configuration
The udm.Environment ci has 2 new properties:
* pausable (boolean)
* pauseOrder

## Usage

If pause property is true, the plugin will add a pause step a the given order for all deployments targeting this environment.

## Pause Step for the XL Rule engine. 

#### Description

The `pause` step pauses the deployment plan.

#### Examples

This is an example of the `pause` step created with an XML rule. It pauses the plan using 70 as the `order` value

    <pause>
        <order>70</order>
    </pause>


This is an example of a fully-specified step created with an XML rule. It combines the order and the description attributes.

    <pause>
        <description expression="true">"Pause the plan for the %s deployed " % (deployed.name)</description>
        <order>70</order>        
    </pause>