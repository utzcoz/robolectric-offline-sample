plugins {
    `java-library`
}

val versions = listOf(
    "14-robolectric-10818077-i4",
    "13-robolectric-9030017-i4",
    "12.1-robolectric-8229987-i4",
    "12-robolectric-7732740-i4",
    "11-robolectric-6757853-i4",
    "10-robolectric-5803371-i4",
    "9-robolectric-4913185-2-i4",
    "8.1.0-robolectric-4611349-i4",
    "8.0.0_r4-robolectric-r1-i4",
    "7.1.0_r7-robolectric-r1-i4",
    "7.0.0_r1-robolectric-r1-i4",
    "6.0.1_r3-robolectric-r1-i4",
    "5.1.1_r9-robolectric-r2-i4",
    "5.0.2_r3-robolectric-r0-i4",
    "4.4_r1-robolectric-r2-i4"
)

val downloadTasks = versions.map { version ->
    val configurationName = "robolectric$version".replace(".", "_").replace("-", "_")
    val customConfiguration = configurations.create(configurationName) {
        extendsFrom(configurations.implementation.get())
        isCanBeResolved = true
        isCanBeConsumed = false
    }

    dependencies {
        add(configurationName, "org.robolectric:android-all-instrumented:$version")
    }

    val jarFileDirectory = customConfiguration.resolve().map { it.parentFile.absolutePath }
    val allFilesInDirectory = jarFileDirectory.flatMap { fileTree(it).files }

    val downloadTask = tasks.register<Copy>("downloadRobolectricJars$version") {
        from(allFilesInDirectory)
        into("preinstrumented")
    }

    downloadTask
}


val deleteTask = tasks.register<Delete>("deleteRobolectricJars") { delete("preinstrumented") }

tasks.register("downloadAllRobolectricJars") {
    dependsOn(deleteTask)
    dependsOn(downloadTasks)
}