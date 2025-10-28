[tools]
go = "1.24.5"

[vars]
BUILD_DIR = "bin"
BINARY_NAME = "app_name"
VERSION = "V1.0.0"
BUILD_FLAG = '-ldflags="-s -w -X main.Version={{vars.VERSION}}"'
GO = "go"
GOFLAGS = "-ldflags='-s -w'"

[tasks.build]
description = "Build app_name"
shell = "bash -c"
alias = "b"
run = """
mkdir -p {{vars.BUILD_DIR}}
{{vars.GO}} build {{vars.BUILD_FLAG}} -o {{vars.BUILD_DIR}}/{{vars.BINARY_NAME}}
"""
sources = ["./*.go"]
outputs = ["{{vars.BUILD_DIR}}/{{vars.BINARY_NAME}}"]
quiet = true // hide task running command.

[tasks.run]
description = "Run {{vars.BINARY_NAME}}"
run = "./{{vars.BUILD_DIR}}/{{vars.BINARY_NAME}}"
depends = ["build"]
alias = "r"
