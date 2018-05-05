# i18n
I18n Resource Bundles (properties) from or to JSON maven plugin 

It can happen that you need java resource bundles as *.properties and as *.json and you want them to be in sync.

So just maintain either the *.properties or the *.json files and let the maven plug-in to do the rest.

Usage from Json to properties:
```
<plugin>
    <groupId>ch.vii</groupId>
	<artifactId>i18n-json2properties-maven-plugin</artifactId>
    
    <version>0.0.1</version>
    <executions>
        <execution>
            <id>json2properties</id>
            <configuration>
                <propertiesSourcePath>${basedir}/src/main/resources/fromJson</propertiesSourcePath>
                <jsonTargetPath>${basedir}/src/main/locale</jsonTargetPath>
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
	<artifactId>i18n-json2properties-maven-plugin</artifactId>
    <version>0.0.1</version>
    <executions>
        <execution>
            <id>properties2json</id>
            <configuration>
                <propertiesSourcePath>${basedir}/src/main/resources/fromProperties</propertiesSourcePath>
                <jsonTargetPath>${basedir}/src/main/webcontent/js/locale</jsonTargetPath>
            </configuration>
            <goals>
                <goal>properties2json</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

	<build>
		<plugins>
			<plugin>
				<groupId>ch.vii</groupId>
				<artifactId>i18n-json2properties-maven-plugin</artifactId>
				<version>0.0.1</version>
				<configuration>
					<sourcePath>${sourcePath}</sourcePath>
					<targetPath>${targetPath}</targetPath>
				</configuration>
			</plugin>
		</plugins>
	</build>
	
	
	mvn i18n-json2properties:json2properties -DsourcePath="./src/test/resources/fromJson" -DtargetPath="./target/test/resources2"