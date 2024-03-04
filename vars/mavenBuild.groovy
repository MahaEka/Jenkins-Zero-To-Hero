def call(String gitRepoUrl) {
    git branch: 'main', url: gitRepoUrl
    // Perform Maven build
    sh 'mvn package'
}
