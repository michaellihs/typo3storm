Typo3Storm
==========

PHPStorm plugin for TYPO3 (Extbase) developers. This plugin makes TYPO3 development with PHPStorm more easy.


Features
--------

* Creation of Extbase classes like Controllers, Domain Models, Repositories.
* Creation of test classes for unit tests and functional tests.
* Auto-completion for objects created by t3lib_div::makeInstance
* Auto-completion for objects created by Extbase object manager


Installation
------------

* Clone project
* Open PHPStorm
* Press CMD + , (open preferences pane)
* Go to the "Plugins" section
* Click "Install plugin from disk..."
* Select the typo3storm.jar included in this project, click "OK" and restart PHPStorm


Documentation
-------------

### Configuration ###

* Open the preferences pane (CMD + ,)
* Go to "Typo3Storm Settings" section
* Check "Enable" Checkbox
* Select directory in which TYPO3 is installed (default installation directory is base path of project)


Planned features
----------------

* Extension Kickstarter
* Running TYPO3 unit tests directly in PHPStorm
* Creation of Fluid templates for controller actions
* Auto-complete for Fluid ViewHelper tags and attributes


Acknowledgement
---------------

This project was inspired by the Magicento project (https://github.com/enriquepiatti/Magicento). Some ideas, classes and code-snippets were taken directly from Magicento.
