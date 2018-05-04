# i18n
I18n Resource Bundles (properties) from or to JSON maven plugin 

It can happen that you need java resource bundles as *.properties and as *.json and you want them to be in sync.

So just maintain either the *.properties or the *.json files and let the maven plug-in to do the rest.

Usage from Json to properties:
```
<plugin>
    <groupId>ch.vii</groupId>
	<artifactId>I18nPropertiesFromOrToJson-maven-plugin</artifactId>
    
    <version>0.0.1</version>
    <executions>
        <execution>
            <id>i18n-json2properties</id>
            <configuration>
                <propertiesSourcePath>${basedir}/src/main/resources/fromJson</propertiesSourcePath>
                <jsonTargetPath>${basedir}/src/main/locale</jsonTargetPath>
                <fileWilcard>*.json</fileWilcard>
            </configuration>
            <goals>
                <goal>json2properties</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

Usage from Json to properties:
``` 
<plugin>
    <groupId>ch.vii</groupId>
	<artifactId>I18nPropertiesFromOrToJson-maven-plugin</artifactId>
    <version>0.0.1</version>
    <executions>
        <execution>
            <id>i18n-properties2json</id>
            <configuration>
                <propertiesSourcePath>${basedir}/src/main/resources/fromProperties</propertiesSourcePath>
                <jsonTargetPath>${basedir}/src/main/webcontent/js/locale</jsonTargetPath>
                <fileWilcard>*.properties</fileWilcard>
            </configuration>
            <goals>
                <goal>properties2json</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```
