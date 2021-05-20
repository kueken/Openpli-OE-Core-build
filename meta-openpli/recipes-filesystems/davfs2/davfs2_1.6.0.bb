DESCRIPTION = "A Linux file system driver that allows you to mount a WebDAV server as a disk drive."
SECTION = "network"
PRIORITY = "optional"
HOMEPAGE = "http://dav.sourceforge.net"
DEPENDS = "virtual/gettext neon"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=8f0e2cd40e05189ec81232da84bd6e1a"

PR = "r0"

SRC_URI[md5sum] = "930487ab22664a73fa8938483752eb03"
SRC_URI[sha256sum] = "2e6b675685bd917772be60f0999ae098c80f79ff20dc131e8fec4547cbb63b50"

SRC_URI = "http://download.savannah.nongnu.org/releases/davfs2/${PN}.tar.gz \
           file://neon-config \
           file://volatiles \
           file://davfs2.cfg \
"
DEPENDS = "fuse libdvdread neon"

inherit autotools pkgconfig useradd gettext

USERADD_PACKAGES = "davfs2"
USERADD_PARAM_davfs2 = "--system --home /var/run/mount.davfs \
                        --no-create-home --shell /bin/false \
                        --user-group davfs2"

EXTRA_OECONF = "--with-neon \
                ac_cv_path_NEON_CONFIG=${WORKDIR}/neon-config"


CONFFILES_${PN} = "${sysconfdir}/davfs2/davfs2.conf ${sysconfdir}/davfs2/secrets"

do_install_prepend () {
	cp ${WORKDIR}/davfs2-${PV}/etc/davfs2.conf ${WORKDIR}/build/etc
	cp ${WORKDIR}/davfs2-${PV}/etc/secrets ${WORKDIR}/build/etc
}

do_install_append () {
        mkdir -p ${D}${sysconfdir}/default/volatiles
        install -m 644 ${WORKDIR}/volatiles ${D}${sysconfdir}/default/volatiles/10_davfs2
        rm -rf ${D}${datadir}/davfs2
}
