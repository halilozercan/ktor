kotlin.sourceSets {
    commonMain {
        dependencies {
            api(project(":ktor-client:ktor-client-core"))
        }
    }

    commonTest {
        dependencies {
            api(project(":ktor-client:ktor-client-tests"))
        }
    }
}
