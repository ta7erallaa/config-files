[tools]
go = ""

[vars]
BUILD_DIR = "bin"
BINARY_NAME = "app_name"
VERSION = "V1.0.0"
BUILD_FLAG = '-ldflags="-s -w -X main.Version={{vars.VERSION}}"'
GO = "go"
GOFLAGS = "-ldflags='-s -w'"

[tasks.template]
description =""
shell =""
alias =""
run ="""""" // can be []
sources = []
outputs = []
quiet = true // hide task running command.

// if you need arg, flag, etc for task
usage = '''
arg "<env>" default="value"{
	choises "n" "n2"
}
flag = ""
'''
