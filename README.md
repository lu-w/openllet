Openllet: An Open Source OWL DL reasoner for Java
-----------------------------------------------

[![Build Status](https://api.travis-ci.org/Galigator/openllet.svg?branch=2.6.0-galigator)](https://travis-ci.org/Galigator/openllet)
[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/Galigator/pelletEvolution?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Twitter](https://img.shields.io/badge/twitter-@openllet-blue.svg)](https://twitter.com/openllet)


Openllet is the OWL 2 DL reasoner: 
--------------------------------


Openllet can be used with Jena or OWL-API libraries. Openllet provides functionality to check consistency of ontologies, compute the classification hierarchy, 
explain inferences, and answer SPARQL queries.

Feel free to fork this repository and submit pull requests if you want to see changes, new features, etc. in Pellet.
We need a lot more tests, send your samples if you can.

There are some  code samples in the examples/ directory.
Issues are on [Github](http://github.com/galigator/openllet/issues).

Openllet 2.6.X:
-----------

* Fullify strong typing in openllet core.
* Add support for rdf4j, refactor modules dependencies.
* Enforce interface usage in the core system.

Changes :
* Introduction of version support for latter situation calculus support.

Migration :
* lots of com.clarkparsia.* / com.mindswap.* are refactor into openllet.* to avoid conflict has typing change a lot.

Openllet 2.5.X:
-----------

* full java 8 support, java 8 is a requierement.
* speed and stability improvement

Changes :
* Update versions of libs : owlapi 5, jena3 and lots more. Some old libs have been integrated and cleaned, strongly typed into openllet.
* Corrections : all tests works, no more warnings with high level of reports in Eclipse.

Migration :
* pellet/owlapi/src/main/java/com/clarkparsia/owlapiv3/ is now  pellet/owlapi/src/main/java/com/clarkparsia/owlapiv/
* groupId   com.clarkparsia.pellet   is now   com.github.galigator.openllet

		<dependency>
			<groupId>com.github.galigator.openllet</groupId>
			<artifactId>openllet-owlapi</artifactId>
			<version>2.5.1</version>
		</dependency>
		<dependency>
			<groupId>com.github.galigator.openllet</groupId>
			<artifactId>openllet-jena</artifactId>
			<version>2.5.1</version>
		</dependency>

Dependencies and security of modules of openllet : 
--------------------------------------------------

<table>

<tr><td>Aterm</td><td>
    <a href="https://www.versioneye.com/user/projects/576c2818cd6d51003e92093d"><img src="https://www.versioneye.com/user/projects/576c2818cd6d51003e92093d/badge.svg?style=flat"/></a>
</td></tr>

<tr><td>Core</td><td>
<a href="https://www.versioneye.com/user/projects/576c2675cd6d5100372eab63"><img src="https://www.versioneye.com/user/projects/576c2675cd6d5100372eab63/badge.svg?style=flat"/></a>
</td></tr>

<tr><td>Jena</td><td>
<a href="https://www.versioneye.com/user/projects/576c2679cd6d5100372eab6c"><img src="https://www.versioneye.com/user/projects/576c2679cd6d5100372eab6c/badge.svg?style=flat"/></a>
</td></tr>

<tr><td>OwlApi</td><td>
<a href="https://www.versioneye.com/user/projects/576c268dcd6d510048bab2a7"><img src="https://www.versioneye.com/user/projects/576c268dcd6d510048bab2a7/badge.svg?style=flat"/></a>
</td></tr>

<tr><td>Cli</td><td>
<a href="https://www.versioneye.com/user/projects/576c2675cd6d5100479c7af0"><img src="https://www.versioneye.com/user/projects/576c2675cd6d5100479c7af0/badge.svg?style=flat"/></a>
</td></tr>

<tr><td>Profiler</td><td>
<a href="https://www.versioneye.com/user/projects/576c2690cd6d51003e9207c5"><img src="https://www.versioneye.com/user/projects/576c2690cd6d51003e9207c5/badge.svg?style=flat"/></a>
</td></tr>

<tr><td>Query</td><td>
<a href="https://www.versioneye.com/user/projects/576c2693cd6d5100479c7b05"><img src="https://www.versioneye.com/user/projects/576c2693cd6d5100479c7b05/badge.svg?style=flat"/></a>
</td></tr>

<tr><td>Explanation</td><td>
<a href="https://www.versioneye.com/user/projects/576c2678cd6d5100479c7afb"><img src="https://www.versioneye.com/user/projects/576c2678cd6d5100479c7afb/badge.svg?style=flat"/></a>
</td></tr>

<tr><td>Modularity</td><td>
<a href="https://www.versioneye.com/user/projects/576c268ccd6d51003e9207c1"><img src="https://www.versioneye.com/user/projects/576c268ccd6d51003e9207c1/badge.svg?style=flat"/></a>
</td></tr>

<tr><td>Pellint</td><td>
<a href="https://www.versioneye.com/user/projects/576c268fcd6d5100479c7b00"><img src="https://www.versioneye.com/user/projects/576c268fcd6d5100479c7b00/badge.svg?style=flat"/></a>
</td></tr>

<tr><td>Protege</td><td>
<a href="https://www.versioneye.com/user/projects/576c2693cd6d510048bab2af"><img src="https://www.versioneye.com/user/projects/576c2693cd6d510048bab2af/badge.svg?style=flat"/></a>
</td></tr>

<tr><td>Test</td><td>
<a href="https://www.versioneye.com/user/projects/576c2694cd6d5100372eabcc"><img src="https://www.versioneye.com/user/projects/576c2694cd6d5100372eabcc/badge.svg?style=flat"/></a>
</td></tr>

<tr><td>Distribution</td><td>
<a href="https://www.versioneye.com/user/projects/576c2676cd6d51003e9207b7"><img src="https://www.versioneye.com/user/projects/576c2676cd6d51003e9207b7/badge.svg?style=flat"/></a>
</td></tr>

<tr><td>Examples</td><td>
<a href="https://www.versioneye.com/user/projects/576c2678cd6d510048bab29e"><img src="https://www.versioneye.com/user/projects/576c2678cd6d510048bab29e/badge.svg?style=flat"/></a>
</td></tr>

<tr><td>Parent</td><td>
<a href="https://www.versioneye.com/user/projects/576c2690cd6d5100372eab7d"><img src="https://www.versioneye.com/user/projects/576c2690cd6d5100372eab7d/badge.svg?style=flat"/></a>
</td></tr>

</table>

Pellet 1..2.3] Licences and supports: 
-------------------------------------
 
* [open source](https://github.com/complexible/pellet/blob/master/LICENSE.txt) (AGPL) or commercial license
* pure Java
* developed and [commercially supported](http://complexible.com/) by Complexible Inc. 

Commercial support for Pellet is [available](http://complexible.com/). 
The [Pellet FAQ](http://clarkparsia.com/pellet/faq) answers some frequently asked questions.

There is a [pellet-users mailing list](https://groups.google.com/forum/?fromgroups#!forum/pellet-users) for questions and feedback.
You can search [pellet-users archives](http://news.gmane.org/gmane.comp.web.pellet.user).
Bug reports and enhancement requests should be sent to the mailing list. 

Thanks for using Pellet.
