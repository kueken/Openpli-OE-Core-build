DESCRIPTION = "Classes Without Boilerplate"
HOMEPAGE = "http://www.attrs.org/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d4ab25949a73fe7d4fdee93bcbdbf8ff"

SRC_URI[sha256sum] = "ef6aaac3ca6cd92904cdd0d83f629a15f18053ec84e6432106f7a4d04ae4f5fb"
SRC_URI[md5sum] = "06af884070d9180694becdb106e5cd65"

inherit pypi setuptools

RDEPENDS_${PN}_class-target += " \
    ${PYTHON_PN}-crypt \
    ${PYTHON_PN}-ctypes \
"
RDEPENDS_${PN} += "\
    ${PYTHON_PN}-subprocess \
"

BBCLASSEXTEND = "native"

include python-package-split.inc
