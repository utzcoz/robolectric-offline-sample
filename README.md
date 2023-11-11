# robolectric-offline-sample

This projects demonstrates how to use Robolectric's offline mode with pre-downloaded android-all
jars. We can run `./gradlew downloadAllRobolectricJars` to download necessary android-all jars for
Robolectric into `robolectric-jars/preinstrumented` directory, and then we can run Robolectric
tests directly with pre-downloaded jars.

When you update a new Robolectric version, please read the Robolectric code, and find all necessary
android-all jar's dependencies, and update configuration of `robolectric-jars/build.gradle.kts`.

This repository doesn't contain any android-all jars, so you need to run
`./gradlew downloadAllRobolectricJars` to init android-all jars before running any tests.

Some scripts are powered by [GitHub Copilot Chat](https://docs.github.com/en/copilot/github-copilot-chat/using-github-copilot-chat-in-your-ide).
