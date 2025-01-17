plugins {
    `java-library`
}

val versions = listOf(
    "15-robolectric-12650502-i7",
    "14-robolectric-10818077-i7",
    "13-robolectric-9030017-i7",
    "12.1-robolectric-8229987-i7",
    "12-robolectric-7732740-i7",
    "11-robolectric-6757853-i7",
    "10-robolectric-5803371-i7",
    "9-robolectric-4913185-2-i7",
    "8.1.0-robolectric-4611349-i7",
    "8.0.0_r4-robolectric-r1-i7",
    "7.1.0_r7-robolectric-r1-i7",
    "7.0.0_r1-robolectric-r1-i7",
    "6.0.1_r3-robolectric-r1-i7",
    "5.1.1_r9-robolectric-r2-i7",
    "5.0.2_r3-robolectric-r0-i7",
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
